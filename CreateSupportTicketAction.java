
public class CreateSupportTicketAction {
    @InvocableMethod(label='Create IT Support Ticket' description='Creates a new IT support case.')
    public static List<Result> createTicket(List<Request> requests) {
        List<Result> results = new List<Result>();
        for (Request req : requests) {
            Result res = new Result();
            try {
                Case newCase = new Case(
                    Subject = req.subject,
                    Description = req.description,
                    Origin = 'Chat',
                    SuppliedEmail = req.employeeEmail // You'll need to capture this earlier
                );
                insert newCase;
                res.caseNumber = newCase.CaseNumber;
                res.success = true;
                res.message = 'Support ticket created with case number: ' + res.caseNumber;
            } catch (Exception e) {
                res.success = false;
                res.message = 'Error creating support ticket: ' + e.getMessage();
            }
            results.add(res);
        }
        return results;
    }

    public class Request {
        @InvocableVariable(label='Subject' required=true)
        public String subject;
        @InvocableVariable(label='Description' required=true)
        public String description;
        @InvocableVariable(label='Employee Email')
        public String employeeEmail;
    }

    public class Result {
        @InvocableVariable
        public Boolean success;
        @InvocableVariable
        public String message;
        @InvocableVariable
        public String caseNumber;
    }
}
