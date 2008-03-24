package org.trails.demo;

import org.trails.component.blob.TrailsBlobImpl;
import org.trails.descriptor.annotation.BlobDescriptor;
import org.trails.descriptor.annotation.PropertyDescriptor;
import org.trails.descriptor.extension.BlobDescriptorExtension;
import org.trails.descriptor.extension.ITrailsBlob;

import javax.persistence.*;

@Entity
public class Person
{

	private Integer id;

	private String firstName;

	private String lastName;

	private ITrailsBlob photo = new TrailsBlobImpl();

	private Car car;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PropertyDescriptor(index = 0)
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	private Address address = new Address();

	@Embedded
	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@OneToOne(mappedBy = "owner")
	public Car getCar()
	{
		return car;
	}

	public void setCar(Car car)
	{
		this.car = car;
	}


	/**
	 * Some database engines - for example, MySQL - have different BLOB types for different data sizes.
	 * (TINYBLOB, MEDIUMBLOB, LARGEBLOB)
	 * <p/>
	 * The actual BLOB type used by Hibernate is thus dependent upon the column length, but the default column length of
	 * 255 is often too small to acommodate typical BLOB data.
	 * <p/>
	 * Therefore you'll need to add a Column.length annotation to the property specifying the maximum possible size of the
	 * BLOB data.
	 *
	 * @return
	 */
	@BlobDescriptor(renderType = BlobDescriptorExtension.RenderType.ICON)
	@Lob
	@Column(length = 1048576)  // Use 1Mb maximum length. (MEDIUMBLOB in MySQL.)
	public ITrailsBlob getPhoto()
	{
		return photo;
	}

	public void setPhoto(ITrailsBlob photo)
	{
		this.photo = photo;
	}

	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Person person = (Person) o;

		return getId() != null ? getId().equals(person.getId()) : person.getId() == null;
	}

	public int hashCode()
	{
		return (getId() != null ? getId().hashCode() : 0);
	}
}
