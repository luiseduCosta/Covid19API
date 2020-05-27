package br.com.covid19.api.cases;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MyPage<T> {
	private long currentPage;
	private long totalPages;
	private long totalElements;
	private List<T> elements;
}
