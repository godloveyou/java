package cn.nilaile.ssm.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BlogArticle {
    private String id;

    private String title;

    private String blogDes;

    private Integer clickCount;

    private String isRecommend;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    private Integer categoryId;

    private String userId;

    private String contentHtml;
    
    private String contentMd;
    
    private BlogCategory blogCategory;
    
    private List<BlogTag> listBlogTags;
    
    private List<BlogComment> listBlogComment;

    private Integer tagId;
    
    
    
   


	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public List<BlogComment> getListBlogComment() {
		return listBlogComment;
	}

	public void setListBlogComment(List<BlogComment> listBlogComment) {
		this.listBlogComment = listBlogComment;
	}

	public List<BlogTag> getListBlogTags() {
		return listBlogTags;
	}

	public void setListBlogTags(List<BlogTag> listBlogTags) {
		this.listBlogTags = listBlogTags;
	}

	

	public BlogCategory getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}

	

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    

    public String getBlogDes() {
		return blogDes;
	}

	public void setBlogDes(String blogDes) {
		this.blogDes = blogDes;
	}

	public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend == null ? null : isRecommend.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public String getContentMd() {
		return contentMd;
	}

	public void setContentMd(String contentMd) {
		this.contentMd = contentMd;
	}

  
}