package uz.fidobiznes.budgetauthminfin;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class User implements UserDetails {
    @Size(max = 3, message = "The filial is longer than it should be!")
    private String filial;
    @Size(max = 5, message = "The id field is longer!")
    @Id
    private Integer id;
    @Size(max = 2, message = "The 'deptlevel' field is longer than it should be!")
    private Integer deptlevel;
    @Size(max = 2, message = "The 'struct' field is longer than it should be!")
    private String struct;
    @Size(max = 6, message = "The 'staff' field is longer than it should be!")
    private String staff;
    @Size(max = 60, message = "The 'name' field is longer than it should be!")
    private String name;
    @Size(max = 30, message = "The 'login' field is longer than it should be!")
    private String login;
    @Size(max = 200, message = "The 'password' field is longer than it should be!")
    private String password;
    @Size(max = 1, message = "The 'fullaccess' field is longer than it should be!")
    private String fullaccess = "N";
    @Size(max = 2, message = "The 'maxconnect' field is longer than it should be!")
    private Integer maxconnect = 3;
    @Size(max = 200, message = "The 'listip' field is longer than it should be!")
    private String listip;
    @Size(max = 200, message = "The 'macaddress' field is longer than it should be!")
    private String macaddress;
    @Size(max = 1, message = "The 'isfullacc' field is longer than it should be!")
    private String isfullacc = "N";
    @Size(max = 200, message = "The 'phonenumber' field is longer than it should be!")
    private String phonenumber;
    private Date dateopen;
    private Date dateexpire;
    @Size(max = 2, message = "The 'cntries' field is longer than it should be!")
    private Integer cntries;
    @Size(max = 200, message = "The 'note' field is longer than it should be!")
    private String note;
    @Size(max = 2, message = "The 'action' field is longer than it should be!")
    private Integer action;
    @Size(max = 2, message = "The 'state' field is longer than it should be!")
    private Integer state;
    private Date date_activ;
    private Date date_deact;
    private Date date_correct;
    @Size(max = 6, message = "The 'emp' field is longer than it should be!")
    private Integer emp;
//    private boolean isAccountNonExpired = true;
//    private boolean isAccountNonLocked = true;
//    private boolean isCredentialsNonExpired = true;
//    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
