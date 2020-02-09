package hr.ivlahek.showcase.dto.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserAccountDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String organizationId;

    private List<Integer> mobileApplicationIds = new ArrayList<>();

}
