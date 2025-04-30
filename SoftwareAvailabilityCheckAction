public class SoftwareAvailabilityCheckAction {
    @InvocableMethod(label='Check Software Availability' description='Checks if a software is available for self-installation.')
    public static List<Result> checkAvailability(List<Request> requests) {
        List<Result> results = new List<Result>();
        for (Request req : requests) {
            Result res = new Result();
            // Logic to query your software deployment system
            Boolean isAvailable = checkSoftwareInCatalog(req.softwareName); // Replace with your actual logic
            res.isAvailable = isAvailable;
            res.downloadLink = isAvailable ? getDownloadLink(req.softwareName) : null;
            res.instructionsLink = isAvailable ? getInstructionsLink(req.softwareName) : null;
            res.requiresAdmin = !isAvailable;
            results.add(res);
        }
        return results;
    }

    private static Boolean checkSoftwareInCatalog(String softwareName) {
        // Your logic to check if the software exists and is self-installable
        return true; // Placeholder
    }

    private static String getDownloadLink(String softwareName) {
        // Your logic to retrieve the download link
        return '/software/' + softwareName + '/download'; // Placeholder
    }

    private static String getInstructionsLink(String softwareName) {
        // Your logic to retrieve the installation instructions
        return '/software/' + softwareName + '/instructions'; // Placeholder
    }

    public class Request {
        @InvocableVariable(label='Software Name' required=true)
        public String softwareName;
    }

    public class Result {
        @InvocableVariable
        public Boolean isAvailable;
        @InvocableVariable
        public String downloadLink;
        @InvocableVariable
        public String instructionsLink;
        @InvocableVariable
        public Boolean requiresAdmin;
    }
}
