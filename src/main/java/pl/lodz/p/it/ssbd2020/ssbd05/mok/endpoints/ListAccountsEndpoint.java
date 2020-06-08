package pl.lodz.p.it.ssbd2020.ssbd05.mok.endpoints;

import lombok.extern.java.Log;
import pl.lodz.p.it.ssbd2020.ssbd05.dto.mappers.mok.AccountMapper;
import pl.lodz.p.it.ssbd2020.ssbd05.dto.mok.AccountDTO;
import pl.lodz.p.it.ssbd2020.ssbd05.interceptors.TrackerInterceptor;
import pl.lodz.p.it.ssbd2020.ssbd05.exceptions.io.database.ExceededTransactionRetriesException;
import pl.lodz.p.it.ssbd2020.ssbd05.exceptions.AppBaseException;
import pl.lodz.p.it.ssbd2020.ssbd05.mok.endpoints.interfaces.ListAccountsEndpointLocal;
import pl.lodz.p.it.ssbd2020.ssbd05.mok.managers.AccountManager;
import pl.lodz.p.it.ssbd2020.ssbd05.utils.ResourceBundles;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Endpoint służący do wyświetlania listy kont
 */
@Log
@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
@Interceptors(TrackerInterceptor.class)
public class ListAccountsEndpoint implements Serializable, ListAccountsEndpointLocal {

    /**
     * Menedżer kont
     */
    @Inject
    private AccountManager accountManager;

    /**
     * Metoda pobierająca wszystkie konta
     *
     * @return kolekcja wszystkich kont w systemie Collection<Account>
     * @throws AppBaseException wyjątek aplikacyjny
     */
    @Override
    @RolesAllowed("listAccounts")
    public Collection<AccountDTO> getAllAccounts() throws AppBaseException {
        Collection<AccountDTO> list = new ArrayList<>();
        int callCounter = 0;
        boolean rollback;
        do {
            try {
                list = AccountMapper.INSTANCE.toAccountDTOCollection(accountManager.getAllAccounts());
                rollback = accountManager.isLastTransactionRollback();
            } catch (EJBTransactionRolledbackException e) {
                log.warning("EJBTransactionRolledBack");
                rollback = true;
            }
            if(callCounter > 0)
                log.info("Transaction with ID " + accountManager.getTransactionId() + " is being repeated for " + callCounter + " time");
            callCounter++;
        } while (rollback && callCounter <= ResourceBundles.getTransactionRepeatLimit());
        if (rollback) {
            throw new ExceededTransactionRetriesException();
        }
        return list;
    }

    /**
     * Metoda filtrująca i zwracająca listę kont zgodną z filtrem
     *
     * @param accountFilter filtr
     * @return kolekcja kont Collection<Account>
     * @throws AppBaseException wyjątek aplikacyjny
     */
    @Override
    @RolesAllowed("filterAccounts")
    public Collection<AccountDTO> filterAccounts (String accountFilter) throws AppBaseException {
        Collection<AccountDTO> list = new ArrayList<>();
        int callCounter = 0;
        boolean rollback;
        do {
            try {
                list = AccountMapper.INSTANCE.toAccountDTOCollection(accountManager.filterAccounts(accountFilter));
                rollback = accountManager.isLastTransactionRollback();
            } catch (EJBTransactionRolledbackException e) {
                log.warning("EJBTransactionRolledBack");
                rollback = true;
            }
            if(callCounter > 0)
                log.info("Transaction with ID " + accountManager.getTransactionId() + " is being repeated for " + callCounter + " time");
            callCounter++;
        } while (rollback && callCounter <= ResourceBundles.getTransactionRepeatLimit());
        if (rollback) {
            throw new ExceededTransactionRetriesException();
        }
        return list;
    }
}
