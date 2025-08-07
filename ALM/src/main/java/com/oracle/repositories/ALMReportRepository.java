package com.oracle.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.oracle.entities.ALMReport;
import java.util.List;
public interface ALMReportRepository extends JpaRepository<ALMReport, Long> {
    @Query("SELECT COALESCE(SUM(r.rateSensitiveAssets), 0.0) FROM ALMReport r")
    double findRateSensitiveAssets();
    @Query("SELECT COALESCE(SUM(r.rateSensitiveLiabilities), 0.0) FROM ALMReport r")
    double findRateSensitiveLiabilities();
    @Modifying
    @Query("UPDATE ALMReport ar SET ar.gapValue = :gapValue WHERE ar.id = :id")
    int updateGapValue(@Param("id") Long id, @Param("gapValue") double gapValue);
    @Query("SELECT COALESCE(SUM(r.rateSensitiveAssets), 0.0) FROM ALMReport r WHERE r.scenario.scenarioId = :scenarioId")
    double findRateSensitiveAssetsByScenario(@Param("scenarioId") String scenarioId);
    @Query("SELECT COALESCE(SUM(r.rateSensitiveLiabilities), 0.0) FROM ALMReport r WHERE r.scenario.scenarioId = :scenarioId")
    double findRateSensitiveLiabilitiesByScenario(@Param("scenarioId") String 
scenarioId);
    @Query("SELECT COALESCE(AVG(r.durationGap), 0.0) FROM ALMReport r WHERE r.scenario.scenarioId = :scenarioId")
    double findDurationAssetsByScenario(@Param("scenarioId") String scenarioId);
    @Query("SELECT COALESCE(AVG(r.durationGap), 0.0) FROM ALMReport r WHERE r.scenario.scenarioId = :scenarioId")
    double findDurationLiabilitiesByScenario(@Param("scenarioId") String scenarioId);

}