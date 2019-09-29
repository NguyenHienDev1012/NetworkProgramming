package Week1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MyLab {

	public boolean delete(String path) {
		boolean result = false;
		File inputfile = new File(path);

		// check if this path whether it has exists or not.
		if (!inputfile.exists()) {
			System.out.println("A input file not exists");
			return result;
		}
		// if this path is a filepath -> end.
		if (inputfile.isFile()) {
			System.out.println("A input file is a file path.");
			return result;
		}
		// if this path is a filepath -> ....
		if (inputfile.isDirectory()) {
			// get all files from inputfile and delete it.
			File[] listOffiles = inputfile.listFiles();
			for (File fx : listOffiles) {
				// reverse if each of them is directory.
				delete(fx.getPath());
				fx.delete();
			}
			System.out.println("All files be deleted");
			result = true;
			return result;

		}

		return result;

	}

	public boolean findFirst(String path, String parttern) {
		boolean result = false;
		File inputfile = new File(path);

		// check if this path whether it has exists or not.
		if (!inputfile.exists()) {
			System.out.println("A input file not exists");
			return result;
		} else {
			// if this path is a filepath -> end.
			if (inputfile.isFile()) {
				System.out.println("A input file is a file path.");
				return result;
			} else {
				// if this path is a filepath -> ....
				if (inputfile.isDirectory()) {
					// get all files from inputfile and delete it.
					File[] listOffiles = inputfile.listFiles();
					for (File fx : listOffiles) {
						if (fx.isFile()) {
							if (fx.getName().equalsIgnoreCase(parttern)) {
								System.out.println(fx.getPath());
								result = true;
							}
						}

						else {
							if (fx.isDirectory()) {
								findFirst(fx.getPath(), parttern);
							}
						}
					}

				}
			}
		}

		return result;
	}

	public void findAll(String path, String ext1, String ext2) {
		File inputfile = new File(path);
		// check if this path whether it has exists or not.
		if (!inputfile.exists()) {
			System.out.println("A input file not exists");
		} else {

			// if this path is a filepath -> end.
			if (inputfile.isFile()) {
				System.out.println("A input file is a file path.");
			} else {
				if (inputfile.isDirectory()) {
					File[] list = inputfile.listFiles();
					for (File f : list) {

						if (f.isFile()) {
							String ex = f.getName().split("\\.")[1];
							if (ex.equals(ext1) || ex.equals(ext2)) {
								System.out.println(f.getPath());

							}
						}
						if (f.isDirectory()) {
							findAll(f.getPath(), ext1, ext2);
						}

					}

				}
			}
		}

	}

	public void deleteAll(String path, String ext1, String ext2) {

		File inputfile = new File(path);
		// check if this path whether it has exists or not.
		if (!inputfile.exists()) {
			System.out.println("A input file not exists");
		} else {
			// if this path is a filepath -> end.
			if (inputfile.isFile()) {
				System.out.println("A input file is a file path.");
			} else {
				if (inputfile.isDirectory()) {
					File[] list = inputfile.listFiles();
					for (File f : list) {

						if (f.isFile()) {
							String ex = f.getName().split("\\.")[1];
							if (ex.equals(ext1) || ex.equals(ext2)) {
								f.delete();

							}
						}
						if (f.isDirectory()) {
							deleteAll(f.getPath(), ext1, ext2);
						}

					}

				}

			}
		}
	}

	public void copy(String sDir, String dDir) throws IOException {
		InputStream source = new BufferedInputStream(new FileInputStream(sDir));
		OutputStream des = new BufferedOutputStream(new FileOutputStream(dDir));
		long start = System.currentTimeMillis();
		int data;
		while ((data = source.read()) != -1)
			des.write(data);
		source.close();
		des.close();
		long end = System.currentTimeMillis();
		System.out.println("Copy time:" + (end - start));

	}

	public void copyAll(String sDir, String dDir, String ext1, String ext2)
			throws IOException {
		File input = new File(sDir);
		if (!input.exists()) {
			System.out.println("Path not found");
		} else {
			if (input.isFile()) {
				System.out.println("Path is File");
			} else {
				if (input.isDirectory()) {
					File[] list = input.listFiles();
					for (File f : list) {
						if (f.isFile()) {
							String ex = f.getName().split("\\.")[1];
							if (ex.equals(ext1) || ex.equals(ext2)) {
								InputStream source = new BufferedInputStream(
										new FileInputStream(f.getPath()));

								OutputStream des = new BufferedOutputStream(
										new FileOutputStream(dDir + f.getName()));
								System.out.println("Name-" + f.getName());
								long start = System.currentTimeMillis();
								int data;
								while ((data = source.read()) != -1)
									des.write(data);
								source.close();
								des.close();
								long end = System.currentTimeMillis();
								System.out
										.println("Copy time:" + (end - start));

							}
						} else {
							if (f.isDirectory()) {
								copyAll(f.getPath(), dDir, ext1, ext2);
							}

						}
					}
				}
			}

		}

	}

	public void copyAll2(String sDir, String dDir, String ext1)
			throws IOException {

		File input = new File(sDir);
		File output = new File(dDir);
		if (!input.exists()) {
			System.out.println("Path not found");
		} else {
			if (input.isFile()) {
				System.out.println("Path is File");
			} else if (input.isDirectory()) {
				File[] listFile = input.listFiles();
				for (File file : listFile) {
					if (file.getName().indexOf(ext1) != -1) {
						Files.copy(file.toPath(), output.toPath(),
								StandardCopyOption.REPLACE_EXISTING);
					}
				}
			}
		}
	}

	public boolean copyDir(String source, String des, boolean moved)
			throws IOException {
		File input = new File(source);
		File output = new File(des);

		if (!input.exists()) {
			System.out.println("Path not found");
		} else {
			if (input.isFile()) {
				System.out.println("File is file");
			} else if (input.isDirectory()) {

				if (output.mkdir()) {
					System.out.println("Copy from:" + input + "to" + output);
					File[] list = input.listFiles();
					for (File fx : list) {
						if (fx.isFile()) {
							InputStream s = new BufferedInputStream(
									new FileInputStream(fx.getPath()));
							OutputStream d = new BufferedOutputStream(
									new FileOutputStream(des + "/"
											+ fx.getName()));
							long start = System.currentTimeMillis();
							int data;
							while ((data = s.read()) != -1)
								d.write(data);
							s.close();
							d.close();
							moved = true;
							long end = System.currentTimeMillis();
							System.out.println("Copy time:" + (end - start));
						} else {
							if (fx.isDirectory()) {
								System.out.println("Copy from:"+fx.getPath() +"đến:\t" +des+"/"+fx.getName());
								copyDir(fx.getPath(), des+"/"+fx.getName(), false);
							}

						}

					}

				}
			}
		}

		return moved;
	}

	public static void main(String[] args) throws IOException {
		MyLab lab = new MyLab();
		String path1 = "D:/Test1"; // Not path. => False
		String path2 = "D:/Hien/abc.txt"; // A path is a filepath.=> False
		String path3 = "D:/Hien"; // =>True

		String sDir = "D:/AnhVanCoBan";
		String dDir = "D:/AnhVanCoBan/Copy/";

		// System.out.println(lab.delete(path1));
		// System.out.println(lab.delete(path2));
		// System.out.println(lab.delete(path3));

		// System.out.println(lab.findFirst(path3, "abc.txt"));

		// lab.findAll(path3, "txt", "docx");
		// lab.deleteAll(path3, "txt", "docx");
		// lab.copyAll(sDir, dDir, "docx", "pdf");
		// lab.copy("D:/AnhVanCoBan/Test.docx","D:/AnhVanCoBan/Test01.docx");
		// lab.copy("D:/AnhVanCoBan/av.pdf","D:/AnhVanCoBan/av01.pdf");
		lab.copyDir("D://Folder", "D://Folder1", false);
		// lab.copyAll2(path1, "D:/Test1/xyz.docx", "docx", "");
	}

}
