package SanosSalvos.example.Sanos_y_Salvos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String swaggerDocumentation = "http://localhost:8081/swagger-ui/index.html";

    public ApiResponse(T data) {
        this.data = data;
    }
}
