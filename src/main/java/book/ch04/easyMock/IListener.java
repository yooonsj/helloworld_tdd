package book.ch04.easyMock;

public interface IListener {
    void documentAdded(String title);
    void documentChanged(String title);
    void documentRemoved(String title);
    byte voteForRemoval(String title);
    byte[] voteForRemovals(String[] title);
    int getDocumentSize(String title);
	void documentInfo(String string);
}
