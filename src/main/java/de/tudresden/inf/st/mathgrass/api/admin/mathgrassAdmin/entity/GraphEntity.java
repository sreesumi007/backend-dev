package de.tudresden.inf.st.mathgrass.api.admin.mathgrassAdmin.entity;

import javax.persistence.*;



@Entity
@Table(name = "graph_data")
public class GraphEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graph_id")
    private Long id;

    @Column(columnDefinition = "CLOB")
    private String graphData;

    private String isStudentLoggedIn;

    public GraphEntity() {
    }

    @Override
    public String toString() {
        return "GraphEntity{" +
                "id=" + id +
                ", graphData='" + graphData + '\'' +
                ", isStudentLoggedIn='" + isStudentLoggedIn + '\'' +
                '}';
    }

    public String getIsStudentLoggedIn() {
        return isStudentLoggedIn;
    }

    public void setIsStudentLoggedIn(String isStudentLoggedIn) {
        this.isStudentLoggedIn = isStudentLoggedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGraphData() {
        return graphData;
    }

    public void setGraphData(String graphData) {
        this.graphData = graphData;
    }

    public GraphEntity(Long id, String graphData, String isStudentLoggedIn) {
        this.id = id;
        this.graphData = graphData;
        this.isStudentLoggedIn = isStudentLoggedIn;
    }
}
