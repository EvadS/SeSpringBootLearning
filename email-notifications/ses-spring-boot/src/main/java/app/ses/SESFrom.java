package app.ses;

public enum SESFrom {

    NO_REPLY("stanislav.g@it-dimension.com", "stanislav from My SES Test")
//    NO_REPLY("noreply@mysestest.com", "My SES Test"),
//    SUPPORT("support@mysestest.com", "My SES Support Support")
;

    private final String email;
    private final String name;

    private SESFrom(String email, String name) {
        this.email =email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
