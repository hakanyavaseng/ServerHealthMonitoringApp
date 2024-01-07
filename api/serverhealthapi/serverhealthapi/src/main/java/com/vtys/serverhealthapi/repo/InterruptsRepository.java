package com.vtys.serverhealthapi.repo;

import com.vtys.serverhealthapi.dto.InterruptsReportMonthlyDto;
import com.vtys.serverhealthapi.entity.Interrupts;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@SqlResultSetMapping(
    name = "InterruptsReportMonthlyDtoMapping",
    classes = @ConstructorResult(
        targetClass = InterruptsReportMonthlyDto.class,
        columns = {
            @ColumnResult(name = "MONTH_", type = String.class),
            @ColumnResult(name = "INTERRUPTCOUNT", type = Integer.class)
        }
    )
)

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

    @Query(value = "EXEC sp_ReportInterruptsByMonthly :serverid", nativeQuery = true)
    List<Object[]> getInterruptsReportMonthly(@Param("serverid") Integer serverid);

    @Query(value = "EXEC sp_ReportInterruptsByDaily :serverid", nativeQuery = true)
    List<Object[]> getInterruptsReportDaily(@Param("serverid") Integer serverid);


}
