package gr.hua.dit.ap.vmp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Rating is required.")
    @Min(value = 1, message = "Rating must be between 1 and 5.")
    @Max(value = 5, message = "Rating must be between 1 and 5.")
    @Column(nullable = false)
    private Integer rating;

    @Size(max = 255, message = "Comment must be at most 255 characters.")
    @Column
    private String comment;

    @Column(nullable = false)
    private boolean hidden = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Σχέση One-to-One με Participation
    @OneToOne
    @JoinColumn(name = "participation_id", referencedColumnName = "id")
    private Participation participation;

    // Constructors
    public Review() {}

    public Review(Integer rating, String comment, Participation participation) {
        this.rating = rating;
        this.comment = comment;
        this.participation = participation;
        this.hidden = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public boolean isHidden() { return hidden; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Participation getParticipation() { return participation; }
    public void setParticipation(Participation participation) { this.participation = participation; }
}
