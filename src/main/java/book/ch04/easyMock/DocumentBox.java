package book.ch04.easyMock;

import java.util.*;

public class DocumentBox {
    private final Set<IListener> listeners = new HashSet<IListener>();

    private final Map<String, byte[]> documents = new HashMap<String, byte[]>();

    public void addListener(IListener listener) {
        listeners.add(listener);
    }

	public void addDocument(String title, byte[] document) {
        boolean documentChange = documents.containsKey(title);
        documents.put(title, document);
        if (documentChange) {
            notifyListenersDocumentChanged(title);
        } else {
            notifyListenersDocumentAdded(title);
        }
	}

	public boolean removeDocument(String title) {
        documents.remove(title);
        notifyListenersDocumentRemoved(title);

        return true;
	}
	
	public boolean removeDocuments(String[] titles) {
		// �̱���
		return false;
	}
	
    private void notifyListenersDocumentRemoved(String title) {
        for (IListener listener : listeners) {
            listener.documentRemoved(title);
        }
    }
    
    private void notifyListenersDocumentChanged(String title) {
        for (IListener listener : listeners) {
            listener.documentChanged(title);
        }
    }
    
    private void notifyListenersDocumentAdded(String title) {
        for (IListener listener : listeners) {
            listener.documentAdded(title);
        }
    }

	public String documentInfo(String title) {
		int size = 0;
        for (IListener listener : listeners) {
        	size = listener.getDocumentSize(title);
        	listener.documentInfo(title);
        }

        return title + ": " + size;
	}
}
