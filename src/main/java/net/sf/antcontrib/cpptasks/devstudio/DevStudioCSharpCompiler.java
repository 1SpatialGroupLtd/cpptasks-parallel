package net.sf.antcontrib.cpptasks.devstudio;

import java.io.File;
import java.util.Vector;

import org.apache.tools.ant.types.Environment;

import net.sf.antcontrib.cpptasks.OptimizationEnum;
import net.sf.antcontrib.cpptasks.compiler.CommandLineCompiler;
import net.sf.antcontrib.cpptasks.compiler.LinkType;
import net.sf.antcontrib.cpptasks.compiler.Linker;
import net.sf.antcontrib.cpptasks.compiler.Processor;
import net.sf.antcontrib.cpptasks.parser.Parser;

public class DevStudioCSharpCompiler extends CommandLineCompiler {

	private static Processor instance = new DevStudioCSharpCompiler("csc",
			"/bogus", new String[]{".cs"}, new String[]{}, ".exe", false,
			null, false, null);

	protected DevStudioCSharpCompiler(String command, String identifierArg,
			String[] sourceExtensions, String[] headerExtensions,
			String outputSuffix, boolean libtool,
			CommandLineCompiler libtoolCompiler, boolean newEnvironment,
			Environment env) {
		super(command, identifierArg, sourceExtensions, headerExtensions, outputSuffix,
				libtool, libtoolCompiler, newEnvironment, env);
	}

	public Linker getLinker(LinkType type) {
		// TODO Auto-generated method stub
		throw new RuntimeException("C# compiler unimplemented method");
	}

	protected void addImpliedArgs(Vector args, boolean debug,
			boolean multithreaded, boolean exceptions, LinkType linkType,
			Boolean rtti, OptimizationEnum optimization)
	{
		args.addElement("/nologo");
		if(debug)
			args.addElement("/debug");
		if(optimization.isSize() || optimization.isSpeed())
			args.addElement("/optimize");
		if(linkType.isExecutable())
			//can we check the OS to derive this?
			args.addElement("/target:exe"); //Change to below line when moving to metro
			//args.addElement("/target:appcontainerexe"); // A bit specific to metro, but will do for now.
		else if(linkType.isSharedLibrary())
			args.addElement("/target:library");

		// TODO Add any other default compiler arguments needed.
	}

	protected void addWarningSwitch(Vector args, int warnings)
	{
		// We are modifying a parameter here, but this is ok as we
		// only want to use the modified value inside this function
		if(warnings < 0)
			warnings = 0;
		if(warnings > 4)
		{
			warnings = 4;
			args.addElement("/warnaserror");
		}
		args.addElement("/warn:" + warnings);
	}

	protected void getDefineSwitch(StringBuffer buffer, String define,
			String value) {
		// TODO Auto-generated method stub
		throw new RuntimeException("C# compiler unimplemented method 'getDefineSwitch'");
	}

	protected File[] getEnvironmentIncludePath()
	{
		return new File[]{};
	}

	protected String getIncludeDirSwitch(String source) {
		// TODO Auto-generated method stub
		throw new RuntimeException("C# compiler unimplemented method 'getIncludeDirSwitch'");
	}

	public int getMaximumCommandLength()
	{
		// stay on safe side
        return 32000; // 32767;
	}

	protected void getUndefineSwitch(StringBuffer buffer, String define) {
		// TODO Auto-generated method stub
		throw new RuntimeException("C# compiler unimplemented method 'getUndefineSwitch'");

	}

	protected Parser createParser(File sourceFile) {
		// TODO Auto-generated method stub
		throw new RuntimeException("C# compiler unimplemented method 'createParser'");
	}

	public static Processor getInstance()
	{
		return instance ;
	}

}
