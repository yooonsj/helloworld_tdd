package book.ch04.easyMock;

import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

/**
 * Created by Sangjun on 2015. 8. 9..
 */
public class ExampleTest extends TestCase{
    private DocumentBox documentBox;
    private IListener mockListener;

    protected void setUp() {
        mockListener = createMock(IListener.class);
        documentBox = new DocumentBox();
        documentBox.addListener(mockListener);
    }

    public void testRemoveNonExistingDocument() {
        replay(mockListener);
        documentBox.removeDocument("DocumentNameA");
    }

    public void testAddDocument() {
        mockListener.documentAdded("New Document");
        replay(mockListener);
        documentBox.addDocument("New Document", new byte[0]);
        verify(mockListener);
    }

    public void testAddAndChangeDocument() {
        mockListener.documentAdded("Document");
        mockListener.documentChanged("Document");
        expectLastCall().times(3);
        replay(mockListener);
        documentBox.addDocument("Document", new byte[0]);
        documentBox.addDocument("Document", new byte[0]);
        documentBox.addDocument("Document", new byte[0]);
        documentBox.addDocument("Document", new byte[0]);
        documentBox.addDocument("Document", new byte[0]);
        verify(mockListener);
    }

    public void testVoteForRemoval() {
        mockListener.documentAdded("Document");
        expect(mockListener.getDocumentSize("Document")).andReturn(1024);
        mockListener.documentInfo("Document");
        replay(mockListener);

        documentBox.addDocument("Document", new byte[0]);
        assertEquals("Document: 1024", documentBox.documentInfo("Document"));
        verify(mockListener);
    }
}
