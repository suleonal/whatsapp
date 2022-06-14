package tr.com.argela.whatsapp.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "topic")
    private Friend topic;

    @Column(name = "post_date")
    private String postDate;

    @Column(name = "message")
    private String message;

    @Column(name = "a_user_id")
    private String aUserId;

    @Column(name = "b_user_id")
    private String bUserId;

    @Column(name = "ack_date")
    private String ackDate;
}
