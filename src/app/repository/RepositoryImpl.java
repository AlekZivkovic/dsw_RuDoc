package app.repository;

import app.core.Repository;
import  app.repository.node.RuNode;



public class RepositoryImpl implements Repository {

    private Workspace root;

    public RepositoryImpl(Workspace root) {

        this.root = root;
    }

    public RepositoryImpl() {

        root = new Workspace("Workspace");
    }

    @Override
    public Workspace getWorkspace() {

        return root;
    }

    public Workspace getRoot() {

        return root;
    }

    public void setRoot(Workspace root) {

        this.root = root;
    }
}
