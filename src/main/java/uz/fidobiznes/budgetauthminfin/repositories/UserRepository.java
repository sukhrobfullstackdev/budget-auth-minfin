package uz.fidobiznes.budgetauthminfin.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.fidobiznes.budgetauthminfin.entities.User;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = """
            select 
            e.FILIAL,
            e.ID,
            e.DEPTLEVEL,
            e.STRUCT,
            e.STAFF,
            e.NAME,
            e.LOGIN,
            decode(e.LOGIN,'MFFIDO',emp.DecryptPassword(e.password), '{bcrypt}'||e.PASSWORD) as PASSWORD,
            e.FULLACCESS,
            e.MAXCONNECT,
            e.LISTIP,
            e.MACADDRESS,
            e.ISFULLACC,
            e.PHONENUMBER,
            e.DATEOPEN,
            e.DATEEXPIRE,
            e.CNTTRIES,
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
    Optional<User> findByLogin(@Param("login") String login);
}
