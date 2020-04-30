package pl.lodz.p.it.ssbd2020.ssbd05.mok.endpoints;

import pl.lodz.p.it.ssbd2020.ssbd05.dto.mok.AccountDTO;
import pl.lodz.p.it.ssbd2020.ssbd05.entities.mok.Account;
import pl.lodz.p.it.ssbd2020.ssbd05.mok.managers.AccountManager;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

@Named
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//@RolesAllowed(value = "ADMIN")    TODO Kwesita jest, tego endpointa moze uzywac jeszcze klient, takze chyba jedna adntoacja nie wystarczy
public class EditAccountEndpoint implements Serializable {
    @Inject
    private AccountManager accountManager;
    //Ustawilem tego cczego potrzebowalem do odblokowywania, przy edycji bedzie trzeba dodac reszte
    public AccountDTO findByLogin(String username) {
        AccountDTO accountDTO = new AccountDTO();
        Account account = accountManager.findByLogin(username);
        accountDTO.setLogin(account.getLogin());
        accountDTO.setActive(account.isActive());
        accountDTO.setFailedAuthCounter(account.getFailedAuthCounter());
        return accountDTO;
    }

    public void edit(AccountDTO accountDTO) {
        Account account = accountManager.findByLogin(accountDTO.getLogin());
        account.setFailedAuthCounter(accountDTO.getFailedAuthCounter());
        account.setActive(accountDTO.isActive());
        accountManager.edit(account);
    }

    public void unlockAccount(AccountDTO accountDTO) {
        Account account = accountManager.findByLogin(accountDTO.getLogin());
        account.setFailedAuthCounter(accountDTO.getFailedAuthCounter());
        account.setActive(accountDTO.isActive());
        accountManager.unlockAccount(account);
    }
}