package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProbedatenRepository extends JpaRepository<Probedaten, String> {

	List<Probedaten> getByPROID(String PROID);

	// test query without parameter
	@Query(value = "Select pd.realwert from h2o_probe p join probedaten pd on pd.proid=p.id join parameter par on par.id=pd.parid join Messstelle m on p.mstid=m.id where parnr in ('F125') and m.id in ('8ae5e2f31a15a9e4011a15aa06f900a0') and pd.Stringwert is null", nativeQuery = true)
	List<String> getRealwert();

	// query with parameter für realwerte
	@Query(value = "Select pd.realwert from h2o_probe p join probedaten pd on pd.proid=p.id join parameter par on par.id=pd.parid join Messstelle m on p.mstid=m.id where parnr = :parameterNummer and m.id = :messstellenID", nativeQuery = true)
	List<String> getByMessstelleIDAndParameternummer(@Param("parameterNummer") String parameterNummer,
			@Param("messstellenID") String messstellenID);

	// query für berechnung von occurance - organoleptische
	// ergebnis sind 2 Spalten mit paremetercode + occurance
	@Query(value = "Select distinct stringwert , count(*) occurrence from H2O_PROBE pro , PARAMETER par , PROBEDATEN pd where pro.ID = pd.proid and par.id = pd.parid and par.parnr =  :parameterNummer  and pro.mstid = :messstellenID group by stringwert", nativeQuery = true)
	List<String> getByMessstelleIDAndParameternummerOrganoleptic(@Param("parameterNummer") String parameterNummer,
			@Param("messstellenID") String messstellenID);

	// query für check ob organoleptische bereits vorgkeommen ist
	@Query(value = "Select count(*) from H2O_PROBE pro , PARAMETER par , PROBEDATEN pd where pro.ID = pd.proid and par.id = pd.parid and par.parnr =  :parameterNummer  and pro.mstid = :messstellenID and stringwert = :stringwert", nativeQuery = true)
	Integer getByMessstelleIDAndParameternummerAndStringvalueOrganoleptic(
			@Param("parameterNummer") String parameterNummer, @Param("messstellenID") String messstellenID,
			@Param("stringwert") String stringwert);

}
