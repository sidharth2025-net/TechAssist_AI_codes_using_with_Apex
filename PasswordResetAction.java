public class PasswordResetAction {
    @InvocableMethod(label='Initiate Password Reset' description='Initiates the self-service password reset for a given Email ID.')
    public static List<Result> initiatePasswordReset(List<Request> requests) {
        List<Result> results = new List<Result>();
        for (Request req : requests) {
            Result res = new Result();
            try {
                List<User> users = [SELECT Id, Email FROM User WHERE Email = :req.emailId LIMIT 1];

                if (!users.isEmpty()) {
                    User u = users[0];
                    UserManagement.initSelfServiceForgotPassword(u.Id, true); // Second parameter to send email
                    res.success = true;
                    res.message = 'Password reset email sent to ' + u.Email + '.';
                } else {
                    res.success = false;
                    res.message = 'User with Email ID ' + req.emailId + ' not found.';
                }
            } catch (Exception e) {
                res.success = false;
                res.message = 'Error initiating password reset: ' + e.getMessage();
            }
            results.add(res);
        }
        return results;
    }

    public class Request {
        @InvocableVariable(label='Email ID' required=true)
        public String emailId;
    }

    public class Result {
        @InvocableVariable
        public Boolean success;
        @InvocableVariable
        public String message;
    }
}
