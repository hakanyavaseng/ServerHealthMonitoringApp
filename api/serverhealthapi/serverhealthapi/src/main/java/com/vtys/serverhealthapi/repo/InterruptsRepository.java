package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.entity.Interrupts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface InterruptsRepository extends JpaRepository<Interrupts, Integer> {

    @Query(value = "EXEC sp_GetInterruptsByServerId :serverid", nativeQuery = true)
    List<Interrupts> getInterruptsByServerid(Integer serverid);

    @Query(value = "EXEC GetInterruptsByServerName :servername", nativeQuery = true)
    List<Interrupts> findByServerNameNativeQuery(String servername);

    @Query(value = "EXEC GetInterruptsByHospitalName :hospitalname", nativeQuery = true)
    List<Interrupts>  getInterruptsByHospitalname(String hospitalname);

    @Query(value = "EXEC sp_GetInterruptsByServerIdAndDateRangeInOneWeek :serverid", nativeQuery = true)
    List<Interrupts> getInterruptsInOneWeek(Integer serverid);


    @Query(value = "EXEC sp_GetInterruptsByServerIdAndDateRangeInOneMonth :serverid", nativeQuery = true)
    List<Interrupts> getInterruptsInOneMonth(Integer serverid);

    @Query(value = "EXEC sp_GetInterruptsByServerIdAndDateRangeInOneYear :serverid", nativeQuery = true)
    List<Interrupts> getInterruptsInOneYear(Integer serverid);

    

}
