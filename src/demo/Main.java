package demo;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {

//            File file = new File("resources/bridge.py");
            context.getPolyglotBindings().putMember("javaObj", new MyClass());
//            Source sourcefile = Source.newBuilder("python", file).build();
            Source setsource = Source.newBuilder("python", new File("resources/set.py")).build();
            context.eval("python", "import polyglot\n" +
                    "\n" +
                    "class StringWrap:\n" +
                    "    def __init__(self, x):\n" +
                    "        self.data = x\n" +
                    "\n" +
                    "    def str(self):\n" +
                    "        return self.data\n" +
                    "\n" +
                    "def set():\n" +
                    "    javaObj = polyglot.import_value('javaObj')\n" +
                    "    x_str = StringWrap('set from python')\n" +
                    "    k = x_str.str()\n" +
                    "    javaObj.setText(k)\n" +
                    "\n" +
                    "set()");

            Source getsource = Source.newBuilder("python", new File("resources/get.py")).build();
            String res = context.eval("python", "import polyglot\n" +
                    "\n" +
                    "def get():\n" +
                    "    javaObj =  polyglot.import_value('javaObj')\n" +
                    "    return javaObj.getText()\n" +
                    "get()").asString();
//            context.eval("python",
//                    "def set():\n" +
//                    "\timport polyglot \n" +
//                            "\tjavaObj = polyglot.import_value('javaObj')\n" +
//                            "\tjavaObj.setText('set from python')\n" +
//                            "set()");
//            String res = context.eval("python",
//                    "def get():\n" +
//                            "\timport polyglot \n" +
//                                    "\tjavaObj =  polyglot.import_value('javaObj')\n" +
//                                    "\treturn javaObj.getText()\n" +
//                                    "get()").asString();
            System.out.println(res);
        }
    }
}
