package de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.repository;

import de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.entity.QuestionAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAndAnswerRepository extends JpaRepository<QuestionAnswerEntity,Long> {
}
