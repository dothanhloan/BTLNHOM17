package vn.edu.tlu.dothithanhloan.btlnhom17.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.text.NumberFormat; // Để format tiền tệ
import java.util.Locale; // Để format tiền tệ

import vn.edu.tlu.dothithanhloan.btlnhom17.R; // Import lớp R
import vn.edu.tlu.dothithanhloan.btlnhom17.model.Service; // Import lớp Service

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private Context context;
    private List<Service> serviceList;
    private OnItemActionListener listener;

    public interface OnItemActionListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public ServiceAdapter(Context context, List<Service> serviceList, OnItemActionListener listener) {
        this.context = context;
        this.serviceList = serviceList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_admin, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);

        holder.tvServiceName.setText(service.getName());

        // Format giá tiền cho dễ đọc
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN")); // Định dạng tiền tệ Việt Nam
        String priceFormatted = formatter.format(service.getPriceUnit());
        holder.tvServicePriceUnit.setText("Giá: " + priceFormatted + "/" + service.getUnit());

        // Hiển thị ảnh Avatar (nếu avatar là đường dẫn/URL, sẽ cần thư viện Glide/Picasso)

        String avatarFileName = service.getAvatar(); // Lấy tên file ảnh từ đối tượng Service
        if (avatarFileName != null && !avatarFileName.isEmpty()) {
            // Lấy ID của tài nguyên drawable từ tên file
            int imageResId = context.getResources().getIdentifier(
                    avatarFileName, // Tên file ảnh (ví dụ: "snack_bap")
                    "drawable",     // Loại tài nguyên
                    context.getPackageName() // Tên gói ứng dụng của bạn
            );

            if (imageResId != 0) { // Nếu tìm thấy ID tài nguyên
                holder.ivServiceAvatar.setImageResource(imageResId);
            } else {
                // Nếu không tìm thấy ảnh theo tên, hiển thị ảnh mặc định
                holder.ivServiceAvatar.setImageResource(R.drawable.ic_launcher_background); // Hoặc một ảnh mặc định khác của bạn
                // Toast.makeText(context, "Không tìm thấy ảnh dịch vụ: " + avatarFileName, Toast.LENGTH_SHORT).show(); // Có thể dùng để debug
            }
        } else {
            // Nếu không có tên ảnh, hiển thị ảnh mặc định
            holder.ivServiceAvatar.setImageResource(R.drawable.ic_launcher_background); // Hoặc một ảnh mặc định khác của bạn
        }

        holder.btnEditService.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(position);
            }
        });

        holder.btnDeleteService.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivServiceAvatar;
        TextView tvServiceName, tvServicePriceUnit;
        Button btnEditService, btnDeleteService;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivServiceAvatar = itemView.findViewById(R.id.ivServiceAvatar);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvServicePriceUnit = itemView.findViewById(R.id.tvServicePriceUnit);
            btnEditService = itemView.findViewById(R.id.btnEditService);
            btnDeleteService = itemView.findViewById(R.id.btnDeleteService);
        }
    }

    public void updateServiceList(List<Service> newList) {
        serviceList.clear();
        serviceList.addAll(newList);
        notifyDataSetChanged();
    }
}