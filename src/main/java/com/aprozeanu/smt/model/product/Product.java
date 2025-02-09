package com.aprozeanu.smt.model.product;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    private Set<ProductCategory> categories;

//    @JsonIgnore
//    @Column
//    @Convert(converter = StringToListConverter.class)
//    private List<String> keywords;

    public Product() {
    }

//    public List<String> getKeywords() {
//        return keywords;
//    }
//
//    public void setKeywords(List<String> keywords) {
//        this.keywords = keywords;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
    }
}
