package com.se.demo.trans.entity;

import com.se.manyToOne.entity.Employee2;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Transaction")
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID transactionId;

    private String name3;



//    @AttributeOverrides({
//            @AttributeOverride(
//                    name = "walletID.employeeKey",
//                    column = @Column(name = "EMBEDDED1_SOMEENUM")
//            )
//    })

    @MapsId("employeeKey")
    @ManyToOne
    private Wallet walletSender;

    @MapsId("employeeKey")
    @ManyToOne
    private Wallet walletRecepient;

    public Transaction() {
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getName() {
        return name3;
    }

    public void setName(String name) {
        this.name3 = name;
    }
}