package foi.air.szokpt.transactionmng.dtos.responses;

import java.util.List;

public class TokenValidationResponse {
    private boolean success;
    private String message;
    private List<Data> data;



    public TokenValidationResponse(boolean success, String message, List<Data> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getRole() {
        if (data != null && !data.isEmpty()) {
            return data.getFirst().getRole();
        }
        return null;
    }

    public static class Data {
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
