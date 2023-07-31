package com.gattuso.todoapi.model;

import com.gattuso.todoapi.validation.PendingOrCompleted;
import com.gattuso.todoapi.validation.TodayOrFuture;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "The title cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,50}$",message = "Only numbers and alphabetic character are allowed")
    @Column(name = "title",nullable = false)
    private String title;
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,50}$",message = "Only numbers and alphabetic character are allowed")
    @NotBlank(message = "The description cannot be blank")
    @Column(name = "description",nullable = false)
    private String description;
    @NotBlank(message = "The status cannot be blank")
    @PendingOrCompleted
    @Column(name = "status",nullable = false)
    private String status;
    @TodayOrFuture
    @Column(name = "duedate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

}
