package com.example.cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String seat;
    private BigDecimal price;

    public Ticket(){}

    public Ticket(Long id, String seat, BigDecimal price) {
        this.id = id;
        this.seat = seat;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Ticket))
            return false;
        Ticket other = (Ticket) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() { return 31;}

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seat='" + seat + '\'' +
                ", price=" + price +
                '}';
    }

    @ManyToOne
   @JoinColumn(name = "session_id")
    @JsonIgnore
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
