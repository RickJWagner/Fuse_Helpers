
A Transformer to convert an Exception to a Node, so a Composer like SOAPComposer can handle it.  

    @Transformer
    public Node transformHandlerExceptionToNode(HandlerException from) {
        StringBuilder buf = new StringBuilder()
                .append("<soap:Detail xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope/\">\n")
                .append("\t\t>>>>> Processing failed!!! (transformed to SOAP Fault)\n")
                .append("\t\t>>>>> Detail: [").append(from.getMessage()).append("]\n")
                .append("</soap:Detail>");
        DOMResult dom = new DOMResult();
        try {
            TransformerFactory.newInstance().newTransformer().transform(
                new StreamSource(new StringReader(buf.toString())), dom);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ((Document) dom.getNode()).getDocumentElement();
