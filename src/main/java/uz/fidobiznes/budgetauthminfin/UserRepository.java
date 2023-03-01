package uz.fidobiznes.budgetauthminfin;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("""
            select 
            e.FILIAL,
            e.ID,
            e.DEPTLEVEL,
            e.STRUCT,
            e.STAFF,
            e.NAME,
            e.LOGIN,
            decode(e.LOGIN,'MFFIDO','{noop}'|| emp.DecryptPassword(e.password), '{bcrypt}'||e.PASSWORD) as PASSWORD,
            e.FULLACCESS,
            e.MAXCONNECT,
            e.LISTIP,
            e.MACADDRESS,
            e.ISFULLACC,
            e.PHONENUMBER,
            e.DATEOPEN,
            e.DATEEXPIRE,
            e.CNTRIES,
            e.NOTE,
            e.ACTION,
            e.STATE,
            e.DATE_ACTIV,
            e.DATE_DEACT,
            e.DATE_CORRECT,
            e.EMP
            from employee e
            where upper(e.LOGIN) = UPPER(:login)
            """)
    Optional<User> findByLogin(String login);
}
