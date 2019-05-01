class HTMLInformation {
        private String url;
        private String element;

        HTMLInformation(String url, String element) {
            this.element = element;
            this.url = url;
        }

        String getURL() {
            return url;
        }

        String getElement() {return element; }
}
