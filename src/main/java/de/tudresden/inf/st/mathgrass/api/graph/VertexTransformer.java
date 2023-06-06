package de.tudresden.inf.st.mathgrass.api.graph;

import de.tudresden.inf.st.mathgrass.api.model.VertexDTO;
import de.tudresden.inf.st.mathgrass.api.transform.ModelTransformer;

/**
 * This class can convert {@link VertexDTO} to {@link Vertex} and vice versa.
 */
public class VertexTransformer extends ModelTransformer<VertexDTO, Vertex> {
    /**
     * {@inheritDoc}
     */
    @Override
    public VertexDTO toDto(Vertex entity) {
        VertexDTO v = new VertexDTO();
        v.setId(entity.getId());
        v.setLabel(entity.getLabel());
        v.setX(entity.getX());
        v.setY(entity.getY());
        //Changes for Admin Interface - Starts
        v.setVertexId(entity.getVertexId());
        v.setVertexStroke(entity.getVertexStroke());
        v.setVertexType(entity.getVertexType());
        //Changes for Admin Interface - Ends
        return v;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vertex toEntity(VertexDTO dto) {
        Vertex entity = new Vertex();
        entity.setLabel(dto.getLabel());
        entity.setX(dto.getX());
        entity.setY(dto.getY());
        //Changes for Admin Interface - Starts
        entity.setVertexId(dto.getVertexId());
        entity.setVertexType(dto.getVertexType());
        entity.setVertexStroke(dto.getVertexStroke());
        //Changes for Admin Interface - Ends
        return entity;
    }
}
