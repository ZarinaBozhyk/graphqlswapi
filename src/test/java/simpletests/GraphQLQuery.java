package simpletests;

import lombok.Data;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 8/4/2021
 * #Comments:
 */
@Data
public class GraphQLQuery {
    private String query;
    private Object variables;
}
