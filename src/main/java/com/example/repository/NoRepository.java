package com.example.repository;

import com.example.Model.Node;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface NoRepository extends CrudRepository<Node, Long> {
    //@Query("select n from Node n where n.parent.id is null")
    //List<Node> findAll();


    public List<Node> findByParentId(final Long parentId);

    Node findByid (Long id);

    boolean existsByParentId(Long pareintid);
    Long deleteByParentId(Long pareintid);

    @Query(value = "SELECT max(id) FROM node",nativeQuery = true)
    Long Maxid();

}
