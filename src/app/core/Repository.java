package app.core;



import app.repository.Workspace;

public interface Repository {

    Workspace getWorkspace();
   // void addChild();
   void setRoot(Workspace root);

}
