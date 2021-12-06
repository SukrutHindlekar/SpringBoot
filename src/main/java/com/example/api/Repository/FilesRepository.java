package com.example.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.api.entities.UploadFiles;

@Repository
public interface FilesRepository extends JpaRepository<UploadFiles, String>{
	
	@Query("SELECT t FROM UploadFiles t where t.fileId=:fileid")
	UploadFiles downloadfromDB(String fileid);
}
