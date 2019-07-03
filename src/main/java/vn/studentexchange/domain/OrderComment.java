package vn.studentexchange.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import vn.studentexchange.domain.enumeration.CommentType;

/**
 * A OrderComment.
 */
@Entity
@Table(name = "order_comment")
public class OrderComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "sender")
    private String sender;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private CommentType type;

    @Column(name = "create_at")
    private Instant createAt;

    @ManyToOne
    @JsonIgnoreProperties("comments")
    private OrderCart orderCart;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public OrderComment message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public OrderComment sender(String sender) {
        this.sender = sender;
        return this;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public CommentType getType() {
        return type;
    }

    public OrderComment type(CommentType type) {
        this.type = type;
        return this;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public OrderComment createAt(Instant createAt) {
        this.createAt = createAt;
        return this;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public OrderCart getOrderCart() {
        return orderCart;
    }

    public OrderComment orderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
        return this;
    }

    public void setOrderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderComment orderComment = (OrderComment) o;
        if (orderComment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderComment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderComment{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", sender='" + getSender() + "'" +
            ", type='" + getType() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            "}";
    }
}
