package project.coupon.CouponsOnline;

public class BillPaymentsSdkNegativeScenariosTest {
 /*   private final TestConfiguration configuration = new PropertiesTestConfiguration();
    private final BillPaymentClient client = BillPaymentClientFactory.createDefault(configuration.getSecretKey());

    @Before
    public void checkConfigs() {
        Assume.assumeThat(
                "If there is no keys defined, we skip integration tests",
                Arrays.asList(
                        configuration.getBillIdForRefund(),
                        configuration.getPublicKey(),
                        configuration.getSecretKey()
                ),
                CoreMatchers.everyItem(CoreMatchers.notNullValue())
        );
    }

    @Test
    public void testClientWithIncorrectSecretKey() {
        try {
            //given
            BillPaymentClient badClient = BillPaymentClientFactory.createDefault("incorrectKey");

            //when
            BillResponse response = badClient.getBillInfo("id");
            Assert.fail("This code should be unreachable");

        } catch (BadResponseException e) {
            //then
            Assert.assertEquals(
                    "client should get status unauthorized",
                    HttpStatus.SC_UNAUTHORIZED,
                    e.getHttpStatus()
            );
        }
    }

    @Test
    public void testGetNonexistentBillInfo() {
        try {
            //given
            String nonexistentBillInfoId = "nonexistent";

            //when
            BillResponse response = client.getBillInfo(nonexistentBillInfoId);
            Assert.fail("This code should be unreachable");

        } catch (BillPaymentServiceException e) {
            //then
            Assert.assertEquals(
                    "client should get correct error code",
                    "api.invoice.not.found",
                    e.getResponse().getErrorCode()
            );
        }
    }

    @Test
    public void testCreateIncorrectBill() throws Exception {
        try {
            //given
            CreateBillInfo billWithoutAmount = newIncorrectBillInfo();

            //when
            BillResponse response = client.createBill(billWithoutAmount);
            Assert.fail("This code should be unreachable");

        } catch (BadResponseException e) {
            //then
            Assert.assertEquals(
                    "client should get status unprocessable entity",
                    HttpStatus.SC_UNPROCESSABLE_ENTITY,
                    e.getHttpStatus()
            );
        }
    }

    private CreateBillInfo newIncorrectBillInfo() {
        return new CreateBillInfo(
                UUID.randomUUID().toString(),
                null,
                "test",
                ZonedDateTime.now().plusDays(45),
                new Customer(
                        "test@test.ru",
                        "user uid on your side",
                        "79999999999"
                ),
                "http://test.ru/"
        );
    }*/
}