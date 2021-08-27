package com.cg.bookstore.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="image_model")
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ImageModel {
    public ImageModel() {
		super();
	}

	@Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
	/*
	 * @Column(name = "name") private String name;
	 * 
	 * @Column(name = "type") private String type;
	 */

    @Lob
    @Column(name = "pic")
    private byte[] pic;

//Custom Construtor
    public ImageModel(byte[] pic) {
      //  this.name = name;
       // this.type = type;
        this.pic = pic;
       // this.bookId=bookId;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

}
