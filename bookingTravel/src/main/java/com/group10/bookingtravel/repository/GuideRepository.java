package com.group10.bookingtravel.repository;

import com.group10.bookingtravel.dto.GuideSelectBoxDTO;
import com.group10.bookingtravel.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<Guide,Long> {

    @Query("select distinct new com.group10.bookingtravel.entity.Guide(g.id,g.name,g.phone,g.address) from Guide g where ((lower(g.name) like lower(concat('%',?1,'%')))or(?1 = '' )) and ((lower(g.phone) like lower(concat('%',?2,'%')))or(?2 = '' ))")
    public Optional<List<Guide>> listGuide(String name, String phone);

    @Query(value = "SELECT MAX(ID) FROM guide",nativeQuery = true)
    public Integer getMaxId();

    @Query("select distinct new com.group10.bookingtravel.dto.GuideSelectBoxDTO(g.id,g.name,g.phone,g.address) from Guide g")
    public Optional<List<GuideSelectBoxDTO>> getListGuideSelectbox();
}
