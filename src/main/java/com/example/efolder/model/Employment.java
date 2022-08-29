package com.example.efolder.model;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employments")
public class Employment {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id", nullable = false)
    private UserInfo user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hr_manager_id", nullable = false)
    private UserInfo hrManager;

    @Setter
    @Column(name = "position_name")
    private String positionName;

    @Setter
    @Column(name = "position_description")
    private String positionDescription;

}
