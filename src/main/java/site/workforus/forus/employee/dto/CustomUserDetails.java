package site.workforus.forus.employee.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.workforus.forus.employee.domain.Employee;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final Employee employee;

    public CustomUserDetails(Employee employee){
        this.employee = employee;
    }

    public final Employee getEmployee(){
        return employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return employee.getRoles().stream().map(o -> new SimpleGrantedAuthority((o.getName()))).collect(Collectors.toList()
        );
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getName();
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
