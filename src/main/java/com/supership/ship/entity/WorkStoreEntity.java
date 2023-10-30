package com.supership.ship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workstore")
public class WorkStoreEntity extends BaseEntity{
    @Column(name = "storename")
    private String storeName;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}
