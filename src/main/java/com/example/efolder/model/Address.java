package com.example.efolder.model;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    //    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id")
//    @OneToOne(mappedBy = "address")
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @Setter
    @Column(name = "country")
    private String country;

    @Setter
    @Column(name = "county")
    private String county;

    @Setter
    @Column(name = "city")
    private String city;

    @Setter
    @Column(name = "street")
    private String street;

    @Setter
    @Column(name = "building_number")
    private String buildingNumber;

    @Setter
    @Column(name = "flat_number")
    private String flatNumber;

    @Setter
    @Column(name = "zipcode")
    private String zipcode;


}
