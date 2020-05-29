package br.com.covid19.api.cases;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Page")
@Data @AllArgsConstructor @NoArgsConstructor
public class MyPage<T> {
	private long currentPage;
	private long totalPages;
	private long totalElements;
	private List<T> elements;
}
