package com.lljieeeeee.blog.dto.view;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Lljieeeeee
 * @date 2022/1/21 15:42
 * @url https://www.lljieeeeee.top/
 * @QQ 2015743127
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArchiveDTO", description="")
public class ArchiveDTO {

    private Integer year;

    private Integer month;

    private List<ArchiveArticle> archiveDTOList;
}
