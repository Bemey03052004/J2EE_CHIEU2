import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner x = new Scanner(System.in);

        String msg = """
Chương trình quản lý Sách
1. Thêm 1 cuốn sách
2. Xóa 1 cuốn sách
3. Thay đổi cuốn sách
4. Xuất thông tin tất cả các cuốn sách
5. Tìm sách lập trình (không phân biệt hoa thường)
6. Lấy sách tối đa K theo giá
7. Tìm kiếm theo tác giả
0. Thoát
Chọn chức năng:""";

        int chon = 0;
        do {
            System.out.println(msg);
            chon = x.nextInt();
            x.nextLine(); // Consume newline

            switch (chon) {
                case 1: {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                    System.out.println("Đã thêm sách thành công!");
                    break;
                }
                case 2: {
                    System.out.print("Nhập mã sách cần xóa:");
                    int bookId = x.nextInt();
                    x.nextLine(); // Consume newline
                    boolean removed = listBook.removeIf(book -> book.getId() == bookId);
                    if (removed) {
                        System.out.println("Đã xóa sách thành công!");
                    } else {
                        System.out.println("Không tìm thấy sách với mã này.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Nhập mã sách cần thay đổi:");
                    int bookId = x.nextInt();
                    x.nextLine(); // Consume newline
                    Book bookToUpdate = listBook.stream()
                            .filter(book -> book.getId() == bookId)
                            .findFirst()
                            .orElse(null);

                    if (bookToUpdate != null) {
                        System.out.println("Nhập thông tin mới cho sách:");
                        bookToUpdate.input();
                        System.out.println("Đã cập nhật sách thành công!");
                    } else {
                        System.out.println("Không tìm thấy sách với mã này.");
                    }
                    break;
                }
                case 4: {
                    if (listBook.isEmpty()) {
                        System.out.println("Danh sách sách trống.");
                    } else {
                        listBook.forEach(Book::output);
                    }
                    break;
                }
                case 5: {
                    System.out.print("Nhập từ khóa tìm kiếm (Lập trình):");
                    String keyword = x.nextLine();
                    List<Book> foundBooks = listBook.stream()
                            .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                            .collect(Collectors.toList());
                    if (foundBooks.isEmpty()) {
                        System.out.println("Không tìm thấy sách nào với từ khóa này.");
                    } else {
                        foundBooks.forEach(Book::output);
                    }
                    break;
                }
                case 6: {
                    System.out.print("Nhập số lượng sách K tối đa muốn lấy:");
                    int k = x.nextInt();
                    System.out.print("Nhập giá P tối đa:");
                    double p = x.nextDouble();
                    x.nextLine(); // Consume newline

                    List<Book> topKBooks = listBook.stream()
                            .filter(book -> book.getPrice() <= p)
                            .limit(k)
                            .collect(Collectors.toList());

                    if (topKBooks.isEmpty()) {
                        System.out.println("Không tìm thấy sách nào thỏa mãn điều kiện.");
                    } else {
                        topKBooks.forEach(Book::output);
                    }
                    break;
                }

                case 7: {
                    System.out.print("Nhập danh sách tác giả (cách nhau bởi dấu phẩy): ");
                    String authorsInput = x.nextLine();
                    String[] authorsArray = authorsInput.split(",");

                    Set<String> searchAuthors = new HashSet<>();
                    for (String author : authorsArray) {
                        searchAuthors.add(author.trim().toLowerCase());
                    }

                    List<Book> booksByAuthors = listBook.stream()
                            .filter(book -> searchAuthors.contains(book.getAuthor().toLowerCase()))
                            .collect(Collectors.toList());

                    if (booksByAuthors.isEmpty()) {
                        System.out.println("Không tìm thấy sách nào của các tác giả này.");
                    } else {
                        booksByAuthors.forEach(Book::output);
                    }
                    break;
                }
                case 0: {
                    System.out.println("Thoát chương trình.");
                    break;
                }
                default: {
                    System.out.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
                    break;
                }
            }
            System.out.println(); // Add a newline for better readability between menu iterations
        } while (chon != 0);

        x.close();
    }
}
