package jabpro.testutil;

import java.util.HashSet;
import java.util.Set;

import jabpro.model.person.Address;
import jabpro.model.person.Email;
import jabpro.model.person.Name;
import jabpro.model.person.Person;
import jabpro.model.person.Phone;
import jabpro.model.person.Remark;
import jabpro.model.person.Status;
import jabpro.model.person.StatusTypes;
import jabpro.model.tag.Tag;
import jabpro.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "";


    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Remark remark;

    private Set<Tag> tags;
    private Status status;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        status = new Status(); // default status is preliminary
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }
    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }


    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        switch (status) {
        case "Preliminary":
            this.status.setStatusType(StatusTypes.PRELIMINARY);
            break;
        case "Interviewed":
            this.status.setStatusType(StatusTypes.INTERVIEWED);
            break;
        case "Rejected":
            this.status.setStatusType(StatusTypes.REJECTED);
            break;
        case "Offered":
            this.status.setStatusType(StatusTypes.OFFERED);
            break;
        default:
            this.status.setStatusType(StatusTypes.PRELIMINARY);
        }
        System.out.println(this.status);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, remark, tags);
    }

}
