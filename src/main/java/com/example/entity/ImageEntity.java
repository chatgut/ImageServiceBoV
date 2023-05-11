package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String contentType;
    @Column(length = 50000000)
    private byte[] picByte;




    @Override
    public String toString() {
        return "ImageEntity{" +
                "id=" + id +
                '}';
    }
}
