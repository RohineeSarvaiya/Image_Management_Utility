package Model;

import javax.persistence.*;
@Entity
@Table(name = "image")

public class Image {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private Long imageId;

    @Column(name = "imageName")
    private String imageName;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "Image", columnDefinition = "longblob")
    private byte[] image;

    @Column(name = "Image_Size")
    private double imageSize;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    
    
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public double getImageSize() {
		return imageSize;
	}
	public void setImageSize(double imageSize) {
		this.imageSize = imageSize;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
